package com.sizake.ebank.web.jsonObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class ComplexObject {


    private String s1;

    private Integer i1;

    private BigDecimal b1;

    private Date d1;

    private String[] a1;

    private List<String> ls1;

    private Map<String, String> mss1;

    private SimpleJson[] a01;

    private List<SimpleJson> lo1;

    private Map<String, List<SimpleJson>> mslo1;

    private List<Map<String, String>> lmss1;

    private List<Map<String, List<SimpleJson>>> lmslo1;

//    {
//        "s1": "str1",
//            "i1": 3,
//            "b1": 18.8,
//            "d1": 1533116372903,
//            "a1": ["x1", "x2", "x3"],
//        "ls1": ["y1", "y2", "y3"],
//        "mss1": {
//        "1": "m1",
//                "2": "m2"
//    },
//        "a01": [{
//        "name": "c1",
//                "age": 1
//    }, {
//        "name": "c2",
//                "age": 2
//    }, {
//        "name": "c3",
//                "age": 3
//    }],
//        "lo1": [{
//        "name": "c1",
//                "age": 1
//    }, {
//        "name": "c2",
//                "age": 2
//    }, {
//        "name": "c3",
//                "age": 3
//    }],
//        "mslo1": {
//        "lo3": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo2": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo1": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }]
//    },
//        "lmss1": [{
//        "1": "m1",
//                "2": "m2"
//    }, {
//        "1": "m1",
//                "2": "m2"
//    }, {
//        "1": "m1",
//                "2": "m2"
//    }],
//        "lmslo1": [{
//        "lo3": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo2": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo1": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }]
//    }, {
//        "lo3": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo2": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo1": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }]
//    }, {
//        "lo3": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo2": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }],
//        "lo1": [{
//            "name": "c1",
//                    "age": 1
//        }, {
//            "name": "c2",
//                    "age": 2
//        }, {
//            "name": "c3",
//                    "age": 3
//        }]
//    }]
//    }


}
