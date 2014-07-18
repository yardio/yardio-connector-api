package io.yard.connector.api

import scala.concurrent.Future

import play.api.libs.json.{Reads, Writes}

import io.yard.models.{Organization, Module, ModuleConfiguration, Provider, ProviderConfiguration}

trait Connector {
  def read[T <: ProviderConfiguration](organization: Organization, provider: Provider)(implicit reader: Reads[T]): Future[Option[T]]

  def save[T <: ProviderConfiguration](organization: Organization, provider: Provider)(implicit writer: Writes[T]): Future[Boolean]

  def read[T <: ModuleConfiguration](organization: Organization, module: Module)(implicit reader: Reads[T]): Future[Option[T]]

  def save[T <: ModuleConfiguration](organization: Organization, module: Module)(implicit writer: Writes[T]): Future[Boolean]
}
