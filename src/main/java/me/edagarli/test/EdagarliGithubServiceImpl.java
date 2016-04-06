package me.edagarli.test;

import me.edagarli.test.service.EdagarliGithubService;
import me.edagarli.test.service.HelloEdagarService;
import org.springframework.beans.factory.annotation.Autowired;
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
//        for (int i = 0; i < 1000000; i++) {
//
//        }
        return "http://github.com/edagarli"+","+service.playLOL();
    }

    @Override
    public String projectAddress() {
//        for (int i = 0; i < 1000000; i++) {
//
//        }
        return "https://github.com/edagarli/monitor-example";
    }

    @Autowired
    private HelloEdagarService service;
}
