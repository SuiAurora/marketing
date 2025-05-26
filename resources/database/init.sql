CREATE TABLE `user`
(
    `user_id`     bigint       NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(255) NOT NULL COMMENT '用户名',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `role`        tinyint      NOT NULL COMMENT '角色',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '修改时间',
    `delete_flag` tinyint      NOT NULL COMMENT '删除标识：0=未删除；1=已删除',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

CREATE TABLE `category`
(
    `category_id` bigint       NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `name`        varchar(255) NOT NULL COMMENT '分类名称',
    `order`       int          NOT NULL COMMENT '顺序',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `delete_flag` tinyint      NOT NULL COMMENT '删除标识：0=未删除；1=已删除',
    PRIMARY KEY (`category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='分类表';

CREATE TABLE `content`
(
    `content_id`  bigint       NOT NULL AUTO_INCREMENT COMMENT '内容id',
    `category_id` bigint       NOT NULL COMMENT '分类id',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `content`     text         NOT NULL COMMENT '内容',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `delete_flag` tinyint      NOT NULL COMMENT '删除标识：0=未删除；1=已删除',
    PRIMARY KEY (`content_id`),
    KEY `category_id` (`category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='内容表';