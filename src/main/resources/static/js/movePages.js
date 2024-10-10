/** 商品リストがロードされるときリストの上6件のみ表示　*/
document.addEventListener("DOMContentLoaded", function () {
  let rows = document.getElementsByClassName("t-row");
  if (rows.length > 6) {
    for (let i = 6; i < rows.length; i++) {
      rows[i].classList.add("hidden");
    }
  }
});

/** 次ページを表示する */
const showNextPage = () => {
  let tbody = document.getElementsByTagName("tbody")[0];
  // ページインデックスを更新
  let currPage = parseInt(tbody.getAttribute("class").substr(5));
  currPage += 1;
  tbody.setAttribute("class", "page-" + currPage);
  // 前ページボタンを活性にする
  if (currPage == 2) {
    let prevBtn = document.getElementsByClassName("prev-btn")[0];
    prevBtn.removeAttribute("disabled", "");
  }
  let rows = document.getElementsByClassName('t-row');
  // 今まで表示していたアイテムはクラス名hiddenを追加して非表示にする。
  // そのページで表示するアイテムはクラス名hiddenを削除する。
  let startIdx = (currPage - 2) * 6;
  for (let i = 0; i < rows.length; i++) {
    let ind = rows[i].getAttribute("id").substr(4);
    if (ind >= startIdx && ind < startIdx + 6) {
      rows[i].classList.add("hidden");
    } else if (ind >= startIdx +6 && ind < startIdx + 12) {
	  rows[i].classList.remove("hidden");
    }
  }
  // 最終ページの場合、次ページボタンを非活性にする
  let totalItems = document.getElementsByClassName("items-count")[0];
  let total = parseInt(totalItems.innerText.substr(4));
  let totalPage = Math.ceil(total / 6);
  if (totalPage == currPage) {
	let nextBtn = document.getElementsByClassName("next-btn")[0];
    nextBtn.setAttribute("disabled", "");
  }	
}

/** 前ページを表示する */
const showPrevPage = () => {
  // 元ページインデックスを取得
  let tbody = document.getElementsByTagName("tbody")[0];
  let currPage = parseInt(tbody.getAttribute("class").substr(5));
  currPage -= 1;
  tbody.setAttribute("class", "page-" + currPage);
  let rows = document.getElementsByClassName('t-row');
  // これまで表示していたアイテムを非表示にし、前六項目のクラス名hiddenを削除し表示する。
  let startIdx = (currPage + 1) * 6;
  for (let i = 0; i < rows.length; i++) {
    let ind = rows[i].getAttribute("id").substr(4);
    if (ind < startIdx && ind >= startIdx - 6) {
      rows[i].classList.add("hidden");
    } else if (ind >= startIdx - 12 && ind < startIdx - 6) {
	  rows[i].classList.remove("hidden");
    }
  }
  // １ページ目のとき、前ページボタンを非活性にする
  if (currPage == 1) {
	let btn = document.getElementsByClassName("prev-btn")[0];
    btn.setAttribute("disabled", "");
  }
  // 次ページボタンを活性にする
  let nextBtn = document.getElementsByClassName("next-btn")[0];
  nextBtn.removeAttribute("disabled");
}