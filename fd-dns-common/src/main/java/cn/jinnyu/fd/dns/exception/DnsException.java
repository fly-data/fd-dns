package cn.jinnyu.fd.dns.exception;

import lombok.Getter;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-17
 */
@SuppressWarnings("FieldMayBeFinal")
@Getter
public class DnsException extends RuntimeException {

    protected String code;
    protected String msg;
    protected String issue;

    public DnsException(String code, String msg, String issue) {
        this.code = code;
        this.msg = msg;
        this.issue = issue;
    }

}
