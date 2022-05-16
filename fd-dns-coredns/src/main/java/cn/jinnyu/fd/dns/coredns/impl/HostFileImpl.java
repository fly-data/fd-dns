package cn.jinnyu.fd.dns.coredns.impl;

import cn.jinnyu.fd.dns.coredns.AbstractDnsImpl;
import cn.jinnyu.fd.dns.coredns.constant.ConstCoreDns;
import cn.jinnyu.fd.dns.coredns.exception.EasyException;
import cn.jinnyu.fd.dns.coredns.exception.CoreDnsException;
import cn.jinnyu.fd.dns.coredns.model.CoreDnsConfig;
import cn.jinnyu.fd.dns.model.DnsRecord;
import cn.jinnyu.fd.dns.model.DnsRecord.Action;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

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
    public boolean doAction(DnsRecord record) throws CoreDnsException {
        String ip     = record.getIp();
        String host   = record.getHost();
        Action action = record.getAction();
        try {
            switch (action) {
                // 追加写入
                case ADD -> {
                    Files.writeString(path, record.getIp() + " " + record.getHost(), StandardOpenOption.APPEND);
                    return true;
                }
                // 删除指定行
                case DELETE -> {
                    List<DnsRecord> delete = loadHostsFromFile();
                    delete.removeIf(item -> item.getIp().equals(ip) && item.getHost().equals(host));
                    // TODO 重新写入文件
                    return true;
                }
                // 修改记录
                case MODIFY -> {
                    List<DnsRecord> modify = loadHostsFromFile();
                    // TODO 双向查找记录 并 修改
                }
            }
        } catch (IOException e) {
            throw new CoreDnsException(EasyException.CORE_DNS_IO_FAILED);
        }
        return false;
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
                    String[] array   = line.split(" ");
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
