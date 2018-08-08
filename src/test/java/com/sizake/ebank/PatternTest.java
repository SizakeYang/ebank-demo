package com.sizake.ebank;

import com.sizake.ebank.web.jsonObject.PatternObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternTest {
    private static Logger logger;
    //private static ValidatorFactory factory;
    @Autowired //-->spring boot: LocalValidatorFactoryBean 已经成为了Validator的默认实现
    private Validator validator;

    @BeforeClass
    public static void init() {
        //PatternTest.factory = Validation.buildDefaultValidatorFactory();
        //PatternTest.validator = PatternTest.factory.getValidator();//-->how to create Validator
        PatternTest.logger = LoggerFactory.getLogger(PatternTest.class);
    }

    @AfterClass
    public static void clean() {
        //PatternTest.factory.close();
    }

    @Test
    public void kindsOfErrors() {
        final PatternObject author = new PatternObject();
        author.setStr("/asd/asd////asd");

        final Set<ConstraintViolation<PatternObject>> set = this.validator.validate(author); // validate(T object, Class<?>... groups)
        for (final ConstraintViolation<PatternObject> c : set) {
            c.getConstraintDescriptor().getPayload();
            System.out.println("result:" + c.toString());

//            Set<Class<? extends Payload>> payloads =
//                    c.getConstraintDescriptor().getPayload();
//            for (Class<? extends Payload> payload : payloads) {
//                if (payload == Severity.Error.class) {
//                    severeError = true;
//                    System.out.println("Error: " + c.getPropertyPath() + " " +
//                            c.getMessage());
//                } else if (payload == Severity.Info.class) {
//                    System.out.println("Info: " + violation.getPropertyPath() + " " +
//                            c.getMessage());
//                }
//            }
        }
    }


}

