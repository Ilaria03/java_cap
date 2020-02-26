jQuery.sap.require("sap.ui.qunit.qunit-css");
jQuery.sap.require("sap.ui.thirdparty.qunit");
jQuery.sap.require("sap.ui.qunit.qunit-junit");
QUnit.config.autostart = false;

// We cannot provide stable mock data out of the template.
// If you introduce mock data, by adding .json files in your webapp/localService/mockdata folder you have to provide the following minimum data:
// * At least 3 Domande in the list
// * All 3 Domande have at least one risposte

sap.ui.require([
	"sap/ui/test/Opa5",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Common",
	"sap/ui/test/opaQunit",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/App",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Browser",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Master",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Detail",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/Create",
	"cap/catalogo_domande/Catalogo_Domande/test/integration/pages/NotFound"
], function (Opa5, Common) {
	"use strict";
	Opa5.extendConfig({
		arrangements: new Common(),
		viewNamespace: "cap.catalogo_domande.Catalogo_Domande.view."
	});

	sap.ui.require([
		"cap/catalogo_domande/Catalogo_Domande/test/integration/MasterJourney",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/NavigationJourney",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/NotFoundJourney",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/BusyJourney",
		"cap/catalogo_domande/Catalogo_Domande/test/integration/FLPIntegrationJourney"
	], function () {
		QUnit.start();
	});
});