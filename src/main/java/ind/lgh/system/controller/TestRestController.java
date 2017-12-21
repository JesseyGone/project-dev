package ind.lgh.system.controller;

import ind.lgh.system.domain.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 构建RESTful API与单元测试
 * 1.@RestController=@Controller+@ResponseBody
 * 2.以SysSysUser为例，RESTful风格编写API
 * 3.单元测试-当然可以使用Postman之类的插件测试
 *
 * @author lgh
 * @since 2017-12-21
 */
@RestController
@RequestMapping(value = "/rest/test/user")
public class TestRestController {

    /**
     * 创建线程安全的Map---模拟DB-用于观察CRUD对数据的影响
     */
    static Map<Integer, SysUser> users = Collections.synchronizedMap(new HashMap<Integer, SysUser>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<SysUser> getSysUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表 
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递 
        List<SysUser> r = new ArrayList<>(users.values());
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SysUser")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postSysUser(@ModelAttribute SysUser user) {
        // 处理"/users/"的POST请求，用来创建SysUser 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysUser getSysUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的SysUser信息 
        // url中的id可通过@PathVariable绑定到函数的参数中 
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SysUser")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putSysUser(@PathVariable Integer id, @ModelAttribute SysUser user) {
        // 处理"/users/{id}"的PUT请求，用来更新SysUser信息 
        SysUser u = users.get(id);
        u.setLoginName(user.getLoginName());
        u.setPhone(user.getPhone());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteSysUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除SysUser 
        users.remove(id);
        return "success";
    }

}
