package contracts

org.springframework.cloud.contract.spec.Contract.make {
	ignored() 
	request {
		method 'GET'
		url 'person'
	}
	response {
		status 200

		body([
			"firstName": $(regex('[a-zA-Z]+')),
			"lastName": $(regex('[a-zA-Z]+')),
			"isAlive": $(regex(anyBoolean())),
			"age": $(execute('isValidAge($it)')),
			"address.postalCode": $(regex('[0-9]{5}(-[0-9]{4})?')),
			"gender.type": $(regex('male|female')),
		])
		
		headers {
			contentType('application/json')
		}
	}
}