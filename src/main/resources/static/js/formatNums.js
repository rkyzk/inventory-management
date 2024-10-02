/** 在庫数から前ゼロ削除 */
const formatNumber = (className) => {
  let vals = document.getElementsByClassName(className);
  if (vals.length) vals[0].value = Number(vals[0].value);
}