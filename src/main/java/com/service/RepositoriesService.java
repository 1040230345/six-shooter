package com.service;

import com.dto.RepositoriesDto;

/**
 * 仓库接口
 * 时间：2019年8月23日16:22:46
 */
public interface RepositoriesService {
    /**
     * 实现仓库创建
     */
    boolean mkdirRepositories(RepositoriesDto repositoriesDto);
}
