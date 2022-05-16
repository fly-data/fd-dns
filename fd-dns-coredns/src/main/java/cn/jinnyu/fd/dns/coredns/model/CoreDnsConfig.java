package cn.jinnyu.fd.dns.coredns.model;

import lombok.Data;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-16
 */
@Data
public class CoreDnsConfig {

    public enum Implement {
        GRPC, HOST_FILE
    }

    private Implement impl;
    private Grpc      grpc;
    private HostFile  hostFile;

    @Data
    public static class Grpc {
        private String  host;
        private Integer port;
        private String  caFile;
        private String  certFile;
        private String  keyFile;
    }

    @Data
    public static class HostFile {
        private String path;
    }

}
