package com.tangpian.sna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.model.User;

@Service
public class UserService {

        @Autowired
        private UserDao userDao;

        public void createUser(User user) {
                userDao.add(user);
        }

		public List<User> listAll() {
			return userDao.list();
		}

}