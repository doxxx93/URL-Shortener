package me.doxxx.server.urlshortener;

import io.seruco.encoding.base62.Base62;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Base62Conversion {

    private static final Base62 BASE_62 = Base62.createInstance();

    public static String encode(Long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        byte[] bytes = buffer.array();
        byte[] encoded = BASE_62.encode(bytes);
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static long decode(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] decoded = BASE_62.decode(bytes);
        return ByteBuffer.wrap(decoded).getLong();
    }
}
