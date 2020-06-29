package com.demo.practice;

import org.junit.runner.RunWith;
import com.demo.practice.dao.primary.Citya;
import com.demo.practice.dao.primary.CityaRp;
import com.demo.practice.dao.secondary.Voter;
import com.demo.practice.dao.secondary.VoterRp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
class PracticeApplicationTests {

    @Autowired
    CityaRp cityaRp;
    @Autowired
    VoterRp voterRp;

    @Test
    public void testRp() {
        Integer id = 1;
        Citya ca = cityaRp.findByNameLike("Kab");
        System.out.println(ca);
        Optional<Voter> va = voterRp.findById((long)id);
        System.out.println(va);

    }

//    @Test
//    public void contextLoads() {
//    }

}
