export class Ajax {
    static #URL = 'http://localhost:4567/'

    constructor(url) {

    }

    get(uri) {
        return fetch(Ajax.#URL + uri)
    }

    post(uri, data) {
        const option = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }

        return fetch(Ajax.#URL + uri, option)
    }

    delete(uri, data) {
        const option = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }

        return fetch(Ajax.#URL + uri, option)
    }

    patch(uri, data) {
        const option = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }

        return fetch(Ajax.#URL + uri, option)
    }


}