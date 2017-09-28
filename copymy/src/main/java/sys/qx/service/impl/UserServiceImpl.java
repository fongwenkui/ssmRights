package sys.qx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.qx.dao.UserMapper;
import sys.qx.model.User;
import sys.qx.model.UserExample;
import sys.qx.model.UserExample.Criteria;
import sys.qx.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	public User findUserByUsernameAndPassword(String username,String password){
		//封装查询条件
		UserExample userExample=new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		
		//执行查询
		List<User> list = userMapper.selectByExample(userExample);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean getUserIsExist(String username) {
		UserExample example=new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		return list!=null&&list.size()>0?true:false;
	}

	@Override
	public int addUser(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int delUser(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userMapper.selectByExample(new UserExample());
	}
	
}
