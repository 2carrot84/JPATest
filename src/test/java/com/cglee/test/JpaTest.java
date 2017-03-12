package com.cglee.test;

import com.cglee.dao.UserDao;
import com.cglee.model.UsersEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by eguns on 2017. 3. 12..
 */
public class JpaTest {
    private UserDao dao = new UserDao();
    private UsersEntity user1;
    private UsersEntity user2;
    private UsersEntity user3;

    @Before
    public void setUp() {
        this.user1 = new UsersEntity("gyumee", "park.sc", "springno1");
        this.user2 = new UsersEntity("leegw700", "lee.gw", "springno2");
        this.user3 = new UsersEntity("bumjin", "park.bj", "springno3");
    }

    @Test
    public void addAndGet() {
        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));

        UsersEntity userget1 = dao.getUserById(user1.getId());
        Assert.assertThat(userget1.getName(), is(user1.getName()));
        Assert.assertThat(userget1.getPassword(), is(user1.getPassword()));

        UsersEntity userget2 = dao.getUserById(user2.getId());
        Assert.assertThat(userget2.getName(), is(user2.getName()));
        Assert.assertThat(userget2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void count() {
        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));

        dao.add(user1);
        Assert.assertThat(dao.getCount(), is(1));
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));
        dao.add(user3);
        Assert.assertThat(dao.getCount(), is(3));

        Assert.assertThat(dao.getAllUsers().size(), is(3));
    }

    @Test
    public void deleteAndUpdate() {
        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));

        dao.add(user1);
        Assert.assertThat(dao.getCount(), is(1));
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));
        dao.deleteUser(user2.getId());
        Assert.assertThat(dao.getCount(), is(1));

        user1.setPassword("PassWord");
        dao.updateUser(user1);

        UsersEntity userget1 = dao.getUserById(user1.getId());
        Assert.assertThat(userget1.getName(), is(user1.getName()));
        Assert.assertThat(userget1.getPassword(), is("PassWord"));
    }
}
