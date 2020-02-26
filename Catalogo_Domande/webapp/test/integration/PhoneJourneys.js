jQuery.sap.require("sap.ui.qunit.qunit-css");
jQuery.sap.require("sap.ui.thirdparty.qunit");
jQuery.sap.require("sap.ui.qunit.qunit-junit");
QUnit.config.autostart = false;

sap.ui.require([
	"sap/ui/test/Opa5",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Common",
	"sap/ui/test/opaQunit",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/App",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Browser",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Master",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Detail",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/NotFound"
], function (Opa5, Common) {
	"use strict";
	Opa5.extendConfig({
		arrangements: new Common(),
		viewNamespace: "cap.catalogo_domande.Catalogo_Domande.view."
	});

	sap.ui.require([
		"cap/catalogo_domande/Catalogo_Domande/test/integration/NavigationJourneyPhone",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/NotFoundJourneyPhone",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/BusyJourneyPhone"
	], function () {
		QUnit.start();
	});
});