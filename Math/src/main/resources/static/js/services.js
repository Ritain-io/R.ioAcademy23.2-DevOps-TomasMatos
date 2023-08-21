async function makeServiceRequest(element) {
  try {
    let url = "http://" + window.location.host + element.getAttribute("url");
    console.log("Will make a request to " + url);

    // Hide the divs that are still visible
    hideResultsDivs();

    // Prepare the Request
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: JSON.stringify({
        "value": document.getElementById("inputNumber").value
      }),
      redirect: 'follow'
    };

    // Make the request
    const response = await fetch(url, requestOptions)

    // Check if the request was unsuccessful
    if (!response.ok) {
      console.log('Request failed with status ${response.status}');
    }

    // Parse the JSON data
    const jsonData = await response.json();

    if (jsonData['success']){
        document.getElementById("result_success_span").textContent = jsonData['message'];
        document.getElementById("result_success_div").style.display = 'block';
    }
    else{
        document.getElementById("result_error_span").textContent = jsonData['errorMessage'];
        document.getElementById("result_error_div").style.display = 'block';
    }
    console.log(jsonData)
    console.log(window.location.host);

    return jsonData;
    } catch (error) {
      return null;
    }
 }

 function hideResultsDivs() {
    document.getElementById("result_success_div").style.display = 'none';
    document.getElementById("result_error_div").style.display = 'none';
 }
