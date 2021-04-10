export async function getData(url = '', params = {}) {
  let urlSearchParams = new URLSearchParams();
  for (let key in params) {
    urlSearchParams.append(key, params[key])
  }
  return fetch(`${url}?${urlSearchParams.toString()}`)
  .then(response => {
    if (!response.ok) {
      throw new Error(response.status);
    }
    return response.json();
  })
  .catch(error => console.error('Error:', error));
}

export async function postData(url = '', data = {}) {
  return fetch(url, {
    method: 'POST',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(response.status);
    }
    return response.json()
  })
  .catch(error => console.error('Error:', error));
}
