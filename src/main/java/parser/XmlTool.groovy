package parser

import groovy.xml.MarkupBuilder

class XmlTool {

    public static create() {
        def strXml = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(strXml)
        mb.'?xml'(version: "1.0", encoding: "UTF-8")
        mb.interview {

            data {
                person(id: "05891", comments: "social recruitment") {
                    title {
                        position(code: "P7", "Staff SE")
                    }
                    age("29")
                    assessment(interviewer: "manager1", "tech is ok")
                    experience {
                        phase(from: "2012", to: "now", "CompanyA")
                        phase(from: "2010", to: "2012", "CompanyB")
                    }
                }

                person(id: "05892", comments: "campus recruitment") {
                    title {
                        position(code: "P6", "SE")
                    }
                    age("25")
                    assessment(interviewer: "manager2", "tech is ok")
                    experience {
                        phase(from: "2012", to: "now", "UniversityA")
                        phase(from: "2011", to: "2012", "CompanyB")
                    }
                }
            }
            interviewInfo {
                date("2016-10-12")
                address("meetingroom 402")
                organizator(dept: "cloud", "ZhangSan")
            }
        }

        print strXml
    }


    public static createWithNameSpace() {

        def xmlDoc = new groovy.xml.StreamingMarkupBuilder().bind {
            mkp.xmlDeclaration() // <?xml version='1.0'?>
            mkp.declareNamespace(app: "APP") // ÃüÃû¿Õ¼ä
            mkp.declareNamespace(sizake: "sizake")
            persons {
                comment << "Just comment" // ×¢ÊÍ
                app.person(name: "Mike", address: "ShangHai") {
                    phone(131111111)
                    phone(131222222)
                    sizake.gname("sizake")
                }
                app.person(name: "Lily", address: "BeiJing") {
                    phone(132111111)
                    phone(132222222)
                }
                person(name: "Jack", address: "ShengZhen") {
                    phone(133111111)
                    phone(133222222)
                }
            }
        }
        println xmlDoc
    }

    //object-->xml
    public static create(List list) {
        def strXml = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(strXml)
        mb.'?xml'(version: "1.0", encoding: "UTF-8")
        mb.person {
            name(type: "name", "sizake")
            address "shantou"
            friends {
                for (f in list) {
                    friend f
                }
            }
        }

        print strXml
    }

    public static main(def args) {
        //create()

    }
}
