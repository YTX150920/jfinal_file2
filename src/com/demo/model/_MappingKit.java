package com.demo.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("file", "id", File.class);
		arp.addMapping("permission", "id", Permission.class);
		arp.addMapping("permissions", "id", Permissions.class);
		arp.addMapping("roles", "id", Roles.class);
		// Composite Primary Key order: permission_id,role_id
		arp.addMapping("roles_permissions", "permission_id,role_id", RolesPermissions.class);
		// Composite Primary Key order: role_id,user_id
		arp.addMapping("user_roles", "role_id,user_id", UserRoles.class);
		// Composite Primary Key order: id,username
		arp.addMapping("users", "id,username", Users.class);
	}
}

