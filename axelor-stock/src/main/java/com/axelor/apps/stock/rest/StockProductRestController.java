/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.stock.rest;

import com.axelor.apps.base.db.Company;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.ProductVariant;
import com.axelor.apps.base.db.ProductVariantAttr;
import com.axelor.apps.base.db.ProductVariantValue;
import com.axelor.apps.stock.db.StockLocation;
import com.axelor.apps.stock.rest.dto.StockProductGetRequest;
import com.axelor.apps.stock.rest.dto.StockProductPutRequest;
import com.axelor.apps.stock.rest.dto.StockProductVariantResponse;
import com.axelor.apps.stock.service.StockLocationService;
import com.axelor.apps.tool.api.HttpExceptionHandler;
import com.axelor.apps.tool.api.ObjectFinder;
import com.axelor.apps.tool.api.RequestStructure;
import com.axelor.apps.tool.api.RequestValidator;
import com.axelor.apps.tool.api.ResponseConstructor;
import com.axelor.apps.tool.api.SecurityCheck;
import com.axelor.exception.AxelorException;
import com.axelor.inject.Beans;
import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aos/stock-product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StockProductRestController {

  /**
   * Fetch product stock indicators. Full path to request is
   * /ws/aos/fetch-product-with-stock/{productId}
   */
  @Path("/fetch-product-with-stock/{productId}")
  @POST
  @HttpExceptionHandler
  public Response fetchProductIndicators(
      @PathParam("productId") long productId, StockProductGetRequest requestBody)
      throws AxelorException {
    RequestValidator.validateBody(requestBody);
    new SecurityCheck().readAccess(Product.class).check();

    Product product = ObjectFinder.find(Product.class, productId, requestBody.getVersion());

    Company company = requestBody.getCompany();
    StockLocation stockLocation = requestBody.getStockLocation();

    return Beans.get(StockProductRestService.class)
        .getProductIndicators(product, company, stockLocation);
  }

  /**
   * Modify locker of product in given stock location. Full path to request is
   * /ws/aos/modify-locker/{productId}
   */
  @Path("/modify-locker/{productId}")
  @PUT
  @HttpExceptionHandler
  public Response modifyProductLocker(
      @PathParam("productId") Long productId, StockProductPutRequest requestBody)
      throws AxelorException {
    RequestValidator.validateBody(requestBody);
    new SecurityCheck().writeAccess(Product.class).check();

    Product product = ObjectFinder.find(Product.class, productId, requestBody.getVersion());

    Beans.get(StockLocationService.class)
        .changeProductLocker(requestBody.fetchStockLocation(), product, requestBody.getNewLocker());

    return ResponseConstructor.build(
        Response.Status.OK,
        "Update locker for product with id "
            + product.getId()
            + " to "
            + requestBody.getNewLocker());
  }

  @Path("/get-variant-attributes/{productId}")
  @POST
  @HttpExceptionHandler
  public Response getVariantsAttributes(
      @PathParam("productId") Long productId, RequestStructure requestBody) throws AxelorException {

    RequestValidator.validateBody(requestBody);

    new SecurityCheck()
        .readAccess(
            Arrays.asList(
                Product.class,
                ProductVariant.class,
                ProductVariantAttr.class,
                ProductVariantValue.class))
        .check();

    Product product = ObjectFinder.find(Product.class, productId, requestBody.getVersion());

    return ResponseConstructor.build(
        Response.Status.OK,
        "Request completed",
        new StockProductVariantResponse(
            product, Beans.get(StockProductRestService.class).fetchAttributes(product)));
  }
}
