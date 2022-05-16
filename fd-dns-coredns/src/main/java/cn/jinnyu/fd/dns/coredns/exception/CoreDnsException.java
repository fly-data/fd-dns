package cn.jinnyu.fd.dns.coredns.exception;

import cn.jinnyu.fd.dns.coredns.constant.ConstCoreDns;
import cn.jinnyu.fd.dns.exception.DnsException;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-17
 */
public class CoreDnsException extends DnsException {

    public CoreDnsException(EasyException easy) {
        super(easy.getCode(), easy.getMsg(), easy.getIssue());
    }

    public String print() {
        return "Exception code: " + super.getCode() + ", " + "Desc: " + super.getMsg() + ", " + "More detail: " + ConstCoreDns.DOC_DNS_ROOT + super.getIssue();
    }

}
