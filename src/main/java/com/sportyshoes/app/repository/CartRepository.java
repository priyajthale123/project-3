package com.sportyshoes.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.app.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
