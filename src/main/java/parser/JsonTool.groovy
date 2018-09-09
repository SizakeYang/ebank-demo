package parser

import groovy.json.JsonBuilder

class JsonTool {
    public static create() {
        def contact = [
                [getFirstName: { 'A' }, getLastName: { 'B' }, getTitle: { 'C' }],
                [getFirstName: { 'D' }, getLastName: { 'E' }, getTitle: { 'F' }],
                [getFirstName: { 'G' }, getLastName: { 'H' }, getTitle: { 'I' }]
        ]//object arrat

        def maps = [
                [name: "sizake", order: "1", meaning: "normal me"],
                [name: "kafka", order: "2", meaning: "true me"]
        ]


        def json = new JsonBuilder()

        json.state {
            capital "Denver"
            majorCities "Denver", "Colorado Springs", "Fort Collins"
            others {
                language "english"
                food "beef"
            }
            contacts contact.collect {
                [
                        FirstName: it.getFirstName(),
                        LastName : it.getLastName()
                ]
            }
            lists maps.collect {
                [
                        name : it.get("name"),
                        order: it.get("order")
                ]
            }
        }
        println json
    }
}
