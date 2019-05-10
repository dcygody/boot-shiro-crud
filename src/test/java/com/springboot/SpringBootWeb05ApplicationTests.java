package com.springboot;

import com.springboot.bean.LoginType;
import com.springboot.bean.Role;
import com.springboot.bean.User;
import com.springboot.mapper.RoleMapper;
import com.springboot.mapper.UserRoleMapper;
import com.springboot.service.RolePermissionService;
import com.springboot.service.RoleService;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWeb05ApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Test
	public void contextLoads() {

		String name = "ymm";
		String password = "123456";

		ByteSource salt = ByteSource.Util.bytes(name); //盐
		//f60177de9ea3d62e3ee4c95bb1fe6b16 加密后
		String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
		System.out.println(newPs);

	}

	@Test
	public void test() {
        User user = userService.getUserByName("zs");
        System.out.println(user.getPassword());
	}

	@Test
	public void test02() {
		List<String> roleIdsByUserId = userRoleMapper.getRoleIdsByUserId(5);
		System.out.println(roleIdsByUserId);
	}

	@Test
	public void test03() {
		List<String> permsIdByRoleId = rolePermissionService.getPermsIdByRoleId(3);
		System.out.println(permsIdByRoleId);
	}

	@Test
	public void test04() {
		List<String> roles = Arrays.asList("role1", "role2");
		List<Integer> role1 = roleMapper.getRoleIdByRoleName(roles);
		System.out.println(role1);
	}


	@Test
	public void test05() {

		List<String> roles = userRoleService.getRoleIdsByUserId(3);
		List<Integer> rolesId = roleService.getRoleIdByRoleName(roles); //得到用户角色id集合
		//根据用户角色, 查询用户权限集合
		List<List<String>> perms = new ArrayList<>();
		rolesId.forEach(id -> {
			List<String> perm = rolePermissionService.getPermsIdByRoleId(id);
			perms.add(perm);
		});
		Set<String> permsSet = new HashSet<>();

		for (List<String> list : perms) {
			permsSet.addAll(list);
		}
		System.out.println(permsSet);


	}

	@Test
	public void test06() {
		System.out.println(LoginType.valueOf(LoginType.ADMIN.getType()));
	}



}
