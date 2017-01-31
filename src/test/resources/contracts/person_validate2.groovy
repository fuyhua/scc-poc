package contracts

org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url 'person'
	}
	response {
		status 200

		body([
			"firstName": "Jane",
			"lastName": "Doe",
			"isAlive": true,
			//"age": 88,
			"age": $(execute('isValidAge($it)')),
			"address": [
				"postalCode": "98101",
			],
			"phoneNumbers": [
				[
					"type": "home",
					"number": "999 999-9999",
				]
			],
			"gender": [
				"type": "female",
			],
			"children": [
				[
					"firstName": "Kid",
					"age": 55,
				]
			],
		])
		testMatchers {
			jsonPath('$.firstName', byRegex("[a-zA-Z]+"))
			jsonPath('$.lastName', byRegex("[a-zA-Z]+"))
			jsonPath('$.isAlive', byRegex(anyBoolean()))
			//jsonPath('$.age', byType())		// can we do this if we have custom validation above?
			jsonPath('$.address.postalCode', byRegex("[0-9]{5}(-[0-9]{4})?"))
			jsonPath('$.phoneNumbers', byType {
				minOccurrence(0)				// min occurrence of 1
				maxOccurrence(4)				// max occurrence of 3
			})
			//jsonPath('$.phoneNumbers[*].number', byRegex("[0-9]{3} [0-9]{3}-[0-9]{4}"))
			//jsonPath('$..number', byRegex("[0-9]{3} [0-9]{3}-[0-9]{4}"))
			jsonPath('$.gender.type', byRegex("male|female"))
			jsonPath('$.children', byType {
				minOccurrence(-1)				// min occurrence of 0
				maxOccurrence(21)				// max occurrence of 20
			})
		}
		
		headers {
			contentType('application/json')
		}
	}
}