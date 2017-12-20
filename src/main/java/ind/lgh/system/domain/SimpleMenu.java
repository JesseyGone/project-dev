package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 简单UserRoleMenu权限模型--菜单（权限）
 *
 * @author lgh
 */
@Getter
@Setter
@Entity
@Table(name = "simple_menu")
public class SimpleMenu extends BaseEntity {

    /**
     * 权限code
     */
    @Column(name = "code", nullable = false, length = 30)
    private String code;

    /**
     * 权限名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 资源地址
     */
    @Column(name = "resource", length = 100)
    private String resource;

    /**
     * 角色描述
     */
    @Column(name = "description", length = 240)
    private String description;
}