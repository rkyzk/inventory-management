/** display and hide alert messages */
document.addEventListener("DOMContentLoaded", function () {
  let message = document.getElementById("message");
  // Let messages disappear after 3.5 seconds
  setTimeout(function () {
    message && message.classList.add('hide');
  }, 3500);  
});