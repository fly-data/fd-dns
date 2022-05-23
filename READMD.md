# 飞流数据 `DatabaseMesh` 系列项目 - DNS服务

## 飞流DNS服务

`fd-dns` 服务是飞流数据系列服务中的基础服务, 用于基础DNS解析.

## 设计理念

基于 `Spring Cloud Alibaba` 微服务体系对外提供统一的 `Dubbo3` 接口, 由实现具体实现完成对基础DNS服务的操作.

## 已实现的DNS客户端

- 默认 CoreDNS

  - 完成 HostFile 
  - 排期 Grpc

- 排期 AdGuardDNS
