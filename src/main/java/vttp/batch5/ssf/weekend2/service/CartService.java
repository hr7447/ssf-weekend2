package vttp.batch5.ssf.weekend2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.batch5.ssf.weekend2.model.Cart;
import vttp.batch5.ssf.weekend2.model.User;

@Service
public class CartService {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public User getUser(String userId) {
        User user = redisTemplate.opsForValue().get(userId);
        if (user == null) {
            user = new User(userId);
            redisTemplate.opsForValue().set(userId, user);
        }
        return user;
    }

    public void createCart(String userId, String cartName) {
        User user = getUser(userId);
        user.getCarts().put(cartName, new Cart(cartName));
        redisTemplate.opsForValue().set(userId, user);
    }

    public void addItem(String userId, String cartName, String item, Integer quantity) {
        User user = getUser(userId);
        Cart cart = user.getCarts().get(cartName);
        cart.getItems().put(item, quantity);
        redisTemplate.opsForValue().set(userId, user);
    }

    public void saveCart(String userId, String cartName) {
        User user = getUser(userId);
        redisTemplate.opsForValue().set(userId, user);
    }
}