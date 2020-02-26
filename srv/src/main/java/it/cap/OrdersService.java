package it.cap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.hana.connectivity.cds.CDSException;
import com.sap.cloud.sdk.hana.connectivity.cds.CDSQuery;
import com.sap.cloud.sdk.hana.connectivity.cds.CDSSelectQueryBuilder;
import com.sap.cloud.sdk.service.prov.api.DataSourceHandler;
import com.sap.cloud.sdk.service.prov.api.EntityData;
import com.sap.cloud.sdk.service.prov.api.ExtensionHelper;
import com.sap.cloud.sdk.service.prov.api.annotations.AfterQuery;
import com.sap.cloud.sdk.service.prov.api.annotations.AfterRead;
import com.sap.cloud.sdk.service.prov.api.annotations.BeforeCreate;
import com.sap.cloud.sdk.service.prov.api.annotations.BeforeRead;
import com.sap.cloud.sdk.service.prov.api.annotations.BeforeUpdate;
import com.sap.cloud.sdk.service.prov.api.exits.BeforeCreateResponse;
import com.sap.cloud.sdk.service.prov.api.exits.BeforeReadResponse;
import com.sap.cloud.sdk.service.prov.api.exits.BeforeUpdateResponse;
import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.request.ReadRequest;
import com.sap.cloud.sdk.service.prov.api.request.UpdateRequest;
import com.sap.cloud.sdk.service.prov.api.response.ErrorResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponseAccessor;
import com.sap.cloud.sdk.service.prov.api.response.ReadResponse;
import com.sap.cloud.sdk.service.prov.api.response.ReadResponseAccessor;
import com.sap.cloud.sdk.service.prov.rt.cds.CDSHandler;

public class OrdersService {

	private static final Logger LOG = CloudLoggerFactory.getLogger(OrdersService.class.getName());

	@BeforeRead(entity = "Orders", serviceName = "CatalogService")
	public BeforeReadResponse beforeReadOrders(ReadRequest req, ExtensionHelper h) {
		LOG.error("##### Orders - beforeReadOrders ########");
		return BeforeReadResponse.setSuccess().response();
	}

	@AfterRead(entity = "Orders", serviceName = "CatalogService")
	public ReadResponse afterReadOrders(ReadRequest req, ReadResponseAccessor res, ExtensionHelper h) {
		EntityData ed = res.getEntityData();
		EntityData ex = EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders");
		return ReadResponse.setSuccess().setData(ex).response();
	}

	@AfterQuery(entity = "Orders", serviceName = "CatalogService")
	public QueryResponse afterQueryOrders(QueryRequest req, QueryResponseAccessor res, ExtensionHelper h) {
		List<EntityData> dataList = res.getEntityDataList(); // original list
		List<EntityData> modifiedList = new ArrayList<EntityData>(dataList.size()); // modified list
		for (EntityData ed : dataList) {
			EntityData ex = EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders");
			modifiedList.add(ex);
		}
		return QueryResponse.setSuccess().setData(modifiedList).response();
	}

	@BeforeCreate(entity = "Domande", serviceName = "CatalogService")
	public BeforeCreateResponse beforeCreateProducts(CreateRequest createRequest, ExtensionHelper extensionHelper) {
		Map<String, Object> data = createRequest.getData().asMap();
		data.put("ID", getNewUUID());
		return BeforeCreateResponse.setSuccess()
				.setEntityData(EntityData.createFromMap(data, Arrays.asList("ID"), "Domande")).response();
	}

	@BeforeUpdate(entity = "Risposte", serviceName = "CatalogService")
	public BeforeUpdateResponse beforeCreateRisposte(UpdateRequest updateRequest , ExtensionHelper extensionHelper) {

		Map<String, Object> data = updateRequest.getData().asMap();
		if ((Boolean) data.get("corretta")) {

		

			DataSourceHandler handler = extensionHelper.getHandler();
			// Ricavo handler per CDS
			CDSHandler cdsHandler = (CDSHandler) handler;
			String string = "DOMANDA_ID ='" + (String)data.get("domanda_id")+"'";
			CDSSelectQueryBuilder.WhereBuilder csqb = new CDSSelectQueryBuilder("my.bookshop.Risposte")
					.where(string);
			CDSQuery query = csqb.build();
			try {
				List<EntityData> componentList = cdsHandler.executeQuery(query).getResult();
			} catch (CDSException e) {
				// Return an instance of QueryResponse containing the error in
				// case of failure
				ErrorResponse errorResponse = ErrorResponse.getBuilder()
						.setMessage(e.getMessage())
						.setCause(e)
						.response();
		
				return BeforeUpdateResponse.setError(errorResponse);			}
		
			

		}
		return BeforeUpdateResponse.setSuccess()
				.setEntityData(EntityData.createFromMap(data, Arrays.asList("ID"), "Domande")).response();
	}

	private String getNewUUID() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
}