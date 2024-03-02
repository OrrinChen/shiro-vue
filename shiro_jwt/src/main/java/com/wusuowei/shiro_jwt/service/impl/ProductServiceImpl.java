package com.wusuowei.shiro_jwt.service.impl;

import com.wusuowei.shiro_jwt.entity.Product;
import com.wusuowei.shiro_jwt.mapper.ProductMapper;
import com.wusuowei.shiro_jwt.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
