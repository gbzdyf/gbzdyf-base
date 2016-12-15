package com.cache;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by y on 2016/12/15.
 */
@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Resource
    private CacheContext<Account> cacheContext;
    public void setCacheContext(CacheContext<Account> cacheContext) {
        this.cacheContext = cacheContext;
    }

    public Account getAccountByName(String accountName) {
        Account result = cacheContext.get(accountName);
        if (null != result) {
            logger.info("get from cache... {}", accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);

        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        Account account = accountOptional.get();

        cacheContext.addAndUpdateCache(accountName, account);

        return account;
    }

    public void reload() {
        cacheContext.evictCache();
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}
