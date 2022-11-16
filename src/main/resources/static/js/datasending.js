function sendDataAndDoOrElse(method, url, data, successFunction, failureFunction) {
    // Read CSRF tokens (security measure used by Spring Security)
    const csrf_header = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
    const csrf_token = document.querySelector("meta[name='_csrf']").getAttribute('content');

    // Construct query.
    let http = new XMLHttpRequest();
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader(csrf_header, csrf_token);

    http.onreadystatechange = function () {
        if (this.readyState !== this.DONE) return;

        if (this.status >= 200 && this.status < 300) successFunction();
        if (this.status >= 400 && this.status < 500) failureFunction();
    }

    // Send query.
    http.send(JSON.stringify(data));
}
