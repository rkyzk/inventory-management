/** format price value (add decimal digits & remove leading zeros)*/
const formatPriceValue = () => {
  let prcVals = document.getElementsByClassName('price');
  if (prcVals.length) {
	if (prcVals[0].value) {
	  let price = prcVals[0].value;
	  // remove all periods
      let testStr = price.replaceAll('.','');
      // count # of periods
      let count = price.length - testStr.length;
      // if the input contains only numbers and one period or none
	  if (containsOnlyNumbers(testStr) && count <= 1) {
		// remove leading zeros if any.
	    if (price.startsWith("0")) price = Number(price).toString();
		// add 0 at the end if price has one decimal digit
        if (price.charAt(price.length - 2) == ".") {
          price += "0";
        } else if (price.charAt(price.length - 1) == ".") { 
	      // add two 0s at the end if price ends with a period  
	      price += "00";
        } else if (count == 0) {
	      // add '.00' if price has no decimal digits
          price += ".00";
        }	
	  }  
      prcVals[0].value = price;
	}
  }
};

/** check if the value contains only numbers */
const containsOnlyNumbers = (str) => {
  return /^\d+$/.test(str);
}