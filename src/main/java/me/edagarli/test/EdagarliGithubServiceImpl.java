package me.edagarli.test;

import me.edagarli.test.service.EdagarliGithubService;
import org.springframework.stereotype.Service;

/**
 * User: edagarli
 * Email: lizhi@edagarli.com
 * Date: 16/4/4
 * Time: 11:14
 * Desc:
 */
@Service
public class EdagarliGithubServiceImpl implements EdagarliGithubService {
    @Override
    public String address() {
        for(int i=0;i<1000000;i++){

        }
        return "http://github.com/edagarli";
    }

    @Override
    public String projectAddress() {
        for(int i=0;i<1000000;i++){

        }
        return "https://github.com/edagarli/monitor-example";
    }
}
