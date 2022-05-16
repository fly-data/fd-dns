package cn.jinnyu.fd.dns.coredns.exception;

import lombok.Getter;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-17
 */
@Getter
public enum EasyException {

    CORE_DNS_IO_FAILED("D001", "文件操作异常", "/impl/coredns#io");

    EasyException(String code, String msg, String issue) {
        this.code = code;
        this.msg = msg;
        this.issue = issue;
    }

    private final String code;
    private final String msg;
    private final String issue;

}
