package com.robottitto.tarefapmdm03.api.user;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.robottitto.tarefapmdm03.api.core.AppDatabase;
import com.robottitto.tarefapmdm03.api.user.dao.UserDao;
import com.robottitto.tarefapmdm03.api.user.model.User;

import java.util.List;

public class UserModelService {
    @SuppressLint("StaticFieldLeak")
    private static UserModelService userModelService;
    private UserDao userDao;

    private UserModelService(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase database = Room.databaseBuilder(appContext, AppDatabase.class, AppDatabase.NAME).allowMainThreadQueries().build();
        userDao = database.userDao();
    }

    public static UserModelService get(Context context) {
        if (userModelService == null) {
            userModelService = new UserModelService(context);
        }
        return userModelService;
    }

    public List<User> getUsers() {
        return userDao.getAll();
    }

    public User getUser(String username) {
        return userDao.findByUsername(username);
    }

    public User findUserById(int userId) {
        return userDao.findById(userId);
    }

    public void addUser(User user) {
        userDao.insert(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
