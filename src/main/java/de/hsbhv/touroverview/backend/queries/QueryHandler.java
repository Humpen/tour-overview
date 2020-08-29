package de.hsbhv.touroverview.backend.queries;

import java.util.HashMap;
import java.util.Map;

public interface QueryHandler {
    Map<String,String> headers = new HashMap<>();
    String standardToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImdjbXMtbWFpbi1wcm9kdWN0aW9uIn0.eyJ2ZXJzaW9uIjozLCJpYXQiOjE1OTM0NTI2NTAsImF1ZCI6WyJodHRwczov" +
            "L2FwaS1ldS1jZW50cmFsLTEuZ3JhcGhjbXMuY29tL3YyL2NrYnRmZDFyNTAwZGwwMXo1MXJwZjkzYm0vbWFzdGVyIl0sImlzcyI6Imh0dHBzOi8vbWFuYWdlbWVudC5ncmFwaGNtcy5jb20vIiwic3ViIj" +
            "oiNGEwZWFjZmMtOWU0Zi00YTU3LTg4ODUtNGQ3NWEyMTMyMzM3IiwianRpIjoiY2tjMHNrZTI4MGpvMTAxeG5mNzI2Nm1nOCJ9.wKYXAYAPS80BFruCjmH5rsarB99M0WsH7-86DHBixDCFpSqxxH11aI4TTQVN" +
            "-HvsGXP3OZaO1JDff3-WGLKvgyhBb2QCWtqE4N4jiSLKrjg4pvQs0a8ajeaPL9Dc51PoIjlkDT7GD2iReIbk16U-5WUNwi7bd0yLzNpxkZ_6dpANJcAMFiawDfr3uIlX8Wt61_s8mQlcNt2fOsAyrPqfJkrcBa3stObwT-" +
            "UdDWX5CQuRKOM3tokr1NPsWCxE7orIz_CpgTL61kbru4D4DTtaaZpKd_7DbyPlaWOQeHc6rG3Y1Q2-ZHtrUiRkZ5ipSACSHhaeNgCj3qmq9X3S0ghFkt68tOqGR3tTVqQKcUsxLYECxUzvJFJoW4adZ4ejTIlOLyG4ojnT_6p6JyAXzL" +
            "-hXt6KpXyaSP-mV1asfccz1d3obhmFOjTIkuvF2_kdfr71UdkdqmNhMVHzqeJX9jUXomC-j48jLaWOkHhWpMi0xGsFM-WDo2dt1KDOzqyNU37-9102MLh9ZX_J3il5tgXgUsVsG2XMKGyuEoA9vPe48CwiLNt_skcreSfZmUN3VvO8A0sSxI" +
            "ByvycoU8iRyePwS0i9ZML5gZ32jop7PXT-oCUTxxFr2E-YilNIv-86aMWdVhYB972dAm7udT3vBPBjbWgUOPWyUF0dvL-tKx0fgyM";
    default void setHeaders(String key, String argument){
        headers.put(key, argument);
    }
    default void setAuthToken(String authToken){
//        headers.put("Authorization", "bearer " + authToken);
        headers.put("Authorization", "Bearer " + authToken);
    }
}
