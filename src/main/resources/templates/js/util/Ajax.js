export class Ajax {
    static #URL = 'http://localhost:4567/'

    constructor(url) {

    }

    async get(uri) {
        let result = await fetch(Ajax.#URL + uri)
        if(result.ok) return await result.json()

        throw await result.json()
    }

    async post(uri, data) {
        const option = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data
        }

        let result = await fetch(Ajax.#URL + uri, option)
        if(result.ok) return await result.json()

        throw await result.json()
    }

    async delete(uri, data) {
        const option = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data
        }

        let result = await fetch(Ajax.#URL + uri, option)
        if(result.ok) return await result.json()

        throw await result.json()
    }

    async patch(uri, data) {
        const option = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data
        }

        let result = await fetch(Ajax.#URL + uri, option)
        if(result.ok) return await result.json()

        throw await result.json()
    }


}