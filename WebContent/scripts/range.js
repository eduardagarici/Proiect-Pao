window.onload=function(){
	
window.addEventListener('resize', triggerAll);
document.addEventListener('input', (evt) => RangeInput(
  evt.target
));

triggerAll();

function triggerAll() {
  document
    .querySelectorAll('.range-input')
    .forEach(RangeInput);
}

function RangeInput(input) {
  if (input.type !== 'range') {
    return;
  }

  const range = (input.max - input.min) / 10000;
  const thumbSize = 20;
  const inputHalfWidth = input.clientWidth - thumbSize;
  const balloonPosition = (parseFloat(input.value) * (inputHalfWidth / 1010)) - (thumbSize/2);
  const balloon = input.nextElementSibling;

  balloon.style.left = `${balloonPosition}px`;
  balloon.firstElementChild.textContent = input.value;

  input.style.setProperty('--value', (input.value - input.min) / range);
}
}