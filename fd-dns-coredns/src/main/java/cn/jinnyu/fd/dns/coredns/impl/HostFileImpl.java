package cn.jinnyu.fd.dns.coredns.impl;

import cn.jinnyu.fd.dns.coredns.AbstractDnsImpl;
import cn.jinnyu.fd.dns.coredns.constant.ConstCoreDns;
import cn.jinnyu.fd.dns.coredns.exception.CoreDnsException;
import cn.jinnyu.fd.dns.coredns.exception.EasyException;
import cn.jinnyu.fd.dns.coredns.model.CoreDnsConfig;
import cn.jinnyu.fd.dns.model.DnsAction;
import cn.jinnyu.fd.dns.model.DnsRecord;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-16
 */
@Slf4j
public class HostFileImpl extends AbstractDnsImpl {

    private final String pattern = "(^\\s+)|(\\s+$)|\\s+";
    private final Path   path;

    public HostFileImpl(CoreDnsConfig config) {
        super(config);
        path = Paths.get(config.getHostFile().getPath());
    }

    @Override
    public String name() {
        return ConstCoreDns.IMPL_HOST_FILE;
    }

    @Override
    public boolean doAction(DnsAction action, DnsRecord newRecord, DnsRecord oldRecord) throws CoreDnsException {
        String ip   = newRecord.getIp();
        String host = newRecord.getHost();
        try {
            switch (action) {
                // 追加写入
                case ADD -> {
                    Files.writeString(path, ip + " " + host, StandardOpenOption.APPEND);
                    return true;
                }
                // 删除指定行
                case DELETE -> {
                    List<DnsRecord> delete = loadHostsFromFile();
                    delete.removeIf(item -> item.getIp().equals(ip) && item.getHost().equals(host));
                    List<String> lines = delete.stream().map(item -> item.getIp() + " " + item.getHost()).collect(Collectors.toList());
                    Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
                    return true;
                }
                // 修改记录
                case MODIFY -> {
                    List<DnsRecord> modify = loadHostsFromFile();
                    // @formatter:off
                    modify.stream().
                            filter(item -> item.getIp().equals(oldRecord.getIp()) && item.getHost().equals(oldRecord.getHost())).
                            forEach(item -> {
                                item.setIp(ip);
                                item.setHost(host);
                            });
                    // @formatter:on
                    List<String> lines = modify.stream().map(item -> item.getIp() + " " + item.getHost()).collect(Collectors.toList());
                    Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
                    return true;
                }
                default -> {
                    log.warn("No action matched! [{}]", action);
                    return false;
                }
            }
        } catch (IOException e) {
            throw new CoreDnsException(EasyException.CORE_DNS_IO_FAILED);
        }
    }

    private List<DnsRecord> loadHostsFromFile() throws IOException {
        List<DnsRecord> records = new LinkedList<>();
        String          path    = config.getHostFile().getPath();
        List<String>    hosts   = Files.readAllLines(Paths.get(path));
        // @formatter:off
        hosts.stream().
                filter(line -> null != line && !"".equals(line) && !line.startsWith("#")).
                forEachOrdered(line -> {
                    line = line.replaceAll(pattern, " ").trim();
                    String[]  array  = line.split(" ");
                    DnsRecord record = new DnsRecord();
                    record.setIp(array[0]);
                    record.setHost(array[1]);
                    records.add(record);
                    if (log.isDebugEnabled()) {
                        log.debug("Load from file {} {}", array[0], array[1]);
                    }
                }
        );
        // @formatter:on
        return records;
    }

}
