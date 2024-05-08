package com.healthmate.service.util;

public class JwtUtils {
    private static final String SECRET_KEY = "sdgdthjfhurfgchdhxgsetgvhdrgcdh";

    public static String generateToken(String email) {
        return email;
//        return Jwts.builder()
//                .setSubject(email)
//                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days validity
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
    }
    public static boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }

        return true;
    }

    public static String extractEmail(String token) {
        //return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
        return token;
    }
}
