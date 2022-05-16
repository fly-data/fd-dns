package cn.jinnyu.fd.dns.coredns.impl;

import cn.jinnyu.fd.dns.coredns.AbstractDnsImpl;
import cn.jinnyu.fd.dns.coredns.constant.ConstCoreDns;
import cn.jinnyu.fd.dns.coredns.model.CoreDnsConfig;
import cn.jinnyu.fd.dns.model.DnsRecord;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-16
 */
@Slf4j
public class GrpcImpl extends AbstractDnsImpl {

    public GrpcImpl(CoreDnsConfig config) {
        super(config);
    }

    @Override
    public String name() {
        return ConstCoreDns.IMPL_GRPC;
    }

    @Override
    public boolean doAction(DnsRecord dnsRecord) {
        return false;
    }

}
