window.onload = function () {
		var chart = new CanvasJS.Chart("chartContainer",
		{
			theme: "theme3",
                        animationEnabled: true,
			title:{
				text: "Llamadas Semanales",
				fontSize: 30
			},
			toolTip: {
				shared: true
			},
			axisY: {
				title: "Número de llamadas"
			},
			/*axisY2: {
				title: "million barrels/day"
			},*/
			data: [
			{
				type: "column",
				name: "LLamadas entrantes",
				legendText: "Entrantes",
				showInLegend: true,
				dataPoints:[
				{label: "Lun", y: Android.enviarEntrantes(1)},
				{label: "Mar", y: Android.enviarEntrantes(2)},
				{label: "Miér", y: Android.enviarEntrantes(3)},
				{label: "Jue", y: Android.enviarEntrantes(4)},
				{label: "Vi", y: Android.enviarEntrantes(5)},
				{label: "Sa", y: Android.enviarEntrantes(6)},
				{label: "Dom", y: Android.enviarEntrantes(0)}


				]
			},
			{
				type: "column",
				name: "Llamadas salientes",
				legendText: "Salientes",
				axisYType: "secondary",
				showInLegend: true,
				dataPoints:[
				{label: "Lu", y: Android.enviarSalientes(1)},
                {label: "Ma", y: Android.enviarSalientes(2)},
                {label: "Mie", y: Android.enviarSalientes(3)},
                {label: "Jue", y: Android.enviarSalientes(4)},
                {label: "Vi", y: Android.enviarSalientes(5)},
                {label: "Sa", y: Android.enviarSalientes(6)},
                {label: "Dom", y: Android.enviarSalientes(0)}


				]
			}

			],
          legend:{
            cursor:"pointer",
            itemclick: function(e){
              if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
              	e.dataSeries.visible = false;
              }
              else {
                e.dataSeries.visible = true;
              }
            	chart.render();
            }
          },
        });

chart.render();
}