package ind.lgh.system.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @author lgh
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * @param request
     * @param e
     * @return
     * @throws Exception
     * @ExceptionHandler用来定义函数针对的异常类型，将Exception对象和请求URL映射到error.html
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("status", response.getStatus());
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    /**
     * 分开处理RESTful API异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = RestException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request, RestException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setData("Some Data");
        errorInfo.setUrl(request.getRequestURI());
        return errorInfo;
    }

}
