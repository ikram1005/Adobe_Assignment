
  // Get the signup button and the signup form
  const signupButton = document.querySelector('.signup');
  const signupForm = document.querySelector('#signup');

  // Add an event listener to the signup button
  signupButton.addEventListener('click', () => {
    // Toggle the visibility of the signup form
    signupForm.style.display = (signupForm.style.display === 'none') ? 'block' : 'none';
  });
