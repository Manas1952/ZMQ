package org.example.req_res;

import org.zeromq.ZMQ;

public class VersionReporting {
    public static void main(String[] args) {
        String version = ZMQ.getVersionString();
        int fullVersion = ZMQ.getFullVersion();

        System.out.println(String.format("Version string: %s, Version int: %d", version, fullVersion));
    }
}
