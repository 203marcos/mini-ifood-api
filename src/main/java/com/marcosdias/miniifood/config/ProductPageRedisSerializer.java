package com.marcosdias.miniifood.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosdias.miniifood.product.web.dto.ProductPageResponse;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ProductPageRedisSerializer implements RedisSerializer<ProductPageResponse> {

    private final ObjectMapper objectMapper;

    public ProductPageRedisSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(ProductPageResponse value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Could not serialize ProductPageResponse", e);
        }
    }

    @Override
    public ProductPageResponse deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, ProductPageResponse.class);
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize ProductPageResponse", e);
        }
    }
}

