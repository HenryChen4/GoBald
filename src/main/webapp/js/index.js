function loadStockData(event){
	event.preventDefault()
	document.getElementById("search_form_div").classList.add("hide")
	$.ajax({
        url: "stockInfo",
        data: {
        	stockTicker: document.ticker_search_form.ticker_search_bar.value,
        	field: "ticker_search_bar"
        },
        success: function(result){
            $("#stock_data").html(result)
        }
    })
}