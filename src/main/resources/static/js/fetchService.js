class FetchService {
  get(url) {
    return fetch(url)
    .then(response => response.json())
    .then(function (jsonData) {
      let jsonString = JSON.stringify(jsonData);
      return JSON.parse(jsonString);
    });
  }

  post(url, data) {
    return fetch(url, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(res => res.json())
    .then(function (jsonData) {
      let jsonString = JSON.stringify(jsonData);
      return JSON.parse(jsonString);
    })
  }

  delete(url) {
    fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
}